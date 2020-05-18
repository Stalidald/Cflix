import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { HttpClient } from '@angular/common/http';
import { MovieService } from '../services/movie.service';
import { Movie } from '../models/movie';
import { WarningOptions } from '../models/warning-options';
import { WarningDialogComponent } from '../warning-dialog/warning-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../services/token-storage.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-movie-page',
  templateUrl: './movie-page.component.html',
  styleUrls: ['./movie-page.component.css']
})
export class MoviePageComponent implements OnInit {
  @Input() injectedMovieId: number = 0;
  currentMovie: Movie = new Movie()
  user: User = new User()
  movieIsOwned = false;

  constructor(private route: ActivatedRoute,
    private router: Router, private authenticationService: AuthService, private http: HttpClient, private movieService: MovieService, public dialog: MatDialog, private userService: UserService, private tokenStorageService: TokenStorageService, public _snackBar: MatSnackBar) {
    var movieIdFromUrl = this.route.snapshot.paramMap.get('movieId');
    if (movieIdFromUrl) {
      this.injectedMovieId = Number(movieIdFromUrl)
      this.movieService.getMovieById(this.injectedMovieId).subscribe(x => {
        this.currentMovie = x;
        this.userService.getUserByEmail(this.tokenStorageService.getUser().email).subscribe(x => {
          this.user = x
          for (let ownedMovie of this.user.ownedMovies) {
            if (ownedMovie.id == this.currentMovie.id) {
              this.movieIsOwned = true;
              const tag = document.createElement('script');
              tag.src = "http://www.youtube.com/iframe_api";
              document.body.appendChild(tag);
            }
          }
        });
      })
    } else {
      console.log("ERROR")
    }
  }

  ngOnInit(): void {

  }

  buy(): void {
    const warning = new WarningOptions()
    warning.header = "Figyelmeztetés"
    warning.text = "Biztosan meg akarja venni ezt a filmet? A vásárlás után ennyi lesz az egyenlege: $" + (this.user.balance - this.currentMovie.price)
    warning.optionYes = "Igen, megveszem"
    warning.optionNo = "Mégsem"
    this.openWarningDialog(warning)
  }

  openWarningDialog(warning: WarningOptions): void {
    const dialogRef = this.dialog.open(WarningDialogComponent, {
      width: '400px',
      data: warning
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == warning.optionYes) {
        this.user.balance = this.user.balance - this.currentMovie.price
        this.user.ownedMovies.push(this.currentMovie)
        this.userService.updateUser(this.user).subscribe(x => {
          window.location.reload()
          this.openSnackBar("Sikeres vásárlás", "Értem")
        })
      }
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
