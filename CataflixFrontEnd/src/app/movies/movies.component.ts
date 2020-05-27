import { Component, OnInit, ViewChild } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { Movie } from '../models/movie';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  movies: Movie[] = []
  user: User = new User()
  showOnlyOwnedFilms = false;
  displayedColumns: string[] = ['imageURL', 'title', 'releaseYear', 'category', 'ageLimit', 'rating', 'price', 'view'];
  dataSource = null
  constructor(private route: ActivatedRoute, private router: Router, private movieService: MovieService, private userService: UserService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    if (this.route.snapshot.toString().indexOf('owned') !== -1) {
      this.showOnlyOwnedFilms = true;
      this.userService.getUserByEmail(this.tokenStorageService.getUser().email).subscribe(x => {
        this.user = x
        this.movies = this.user.ownedMovies;
        this.dataSource = new MatTableDataSource(this.movies);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.paginator._intl.itemsPerPageLabel = 'Találatok száma oldalanként:';
        this.dataSource.filterPredicate = (data, filter) =>
          (data.title.trim().toLowerCase().indexOf(filter) !== -1 ||
            data.category.trim().toLowerCase().indexOf(filter) !== -1 ||
            JSON.stringify(data.relatedMovieMembers).toLowerCase().indexOf(filter) !== -1);
      });
    } else {
      this.movieService.getMovies().subscribe(data => {
        this.movies = data;
        this.dataSource = new MatTableDataSource(this.movies);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.paginator._intl.itemsPerPageLabel = 'Találatok száma oldalanként:';
        this.dataSource.filterPredicate = (data, filter) =>
          (data.title.trim().toLowerCase().indexOf(filter) !== -1 ||
            data.category.trim().toLowerCase().indexOf(filter) !== -1 ||
            data.description.trim().toLowerCase().indexOf(filter) !== -1 ||
            JSON.stringify(data.relatedMovieMembers).toLowerCase().indexOf(filter) !== -1);
      });
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  goToMoviePage(movie: Movie) {
    this.router.navigate(['movies/' + movie.id]);
  }
}