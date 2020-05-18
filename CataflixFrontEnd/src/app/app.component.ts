import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { MatDialog } from '@angular/material/dialog';
import { BalanceTopupComponent } from './balance-topup/balance-topup.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from './services/user.service';
import { User } from './models/user';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Cataflix';
  private roles: String[];
  isLoggedIn = false;
  showPremiumBoard = false;
  username: string;
  balanceLabel: number = 0;
  ownedMoviesNumber: number = 0;

  currentUser: User = new User();

  constructor(private tokenStorageService: TokenStorageService, public dialog: MatDialog, public _snackBar: MatSnackBar, public userService: UserService) {
  }

  ngOnInit() {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();

      this.userService.getUserByEmail(user.email).subscribe(x => {
        this.currentUser = x;
        this.roles = this.currentUser.roles
        this.showPremiumBoard = JSON.stringify(this.roles).includes("ROLE_PREMIUM");
        this.username = user.username;
        this.refreshBalance()
      });
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  balancePopup() {
    const dialogRef = this.dialog.open(BalanceTopupComponent, {
      width: '250px',
      data: this.tokenStorageService.getUser()
    });

    dialogRef.afterClosed().subscribe(result => {
      this.refreshBalance()
      if (result == 'topup') {
        this.openSnackBar("Sikeres egyenleg feltöltés", "Értem")
      }
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  refreshBalance() {
    const user = this.tokenStorageService.getUser();
    this.userService.getUserByEmail(user.email).subscribe(x => {
      this.balanceLabel = x.balance;
      this.ownedMoviesNumber = x.ownedMovies.length
    });
  }

  refreshPremiumStatus() {
    this.userService.getUserByEmail(this.currentUser.email).subscribe(x => {
      this.currentUser = x;
      this.roles = this.currentUser.roles
      this.showPremiumBoard = JSON.stringify(this.roles).includes("ROLE_PREMIUM");
      this.refreshBalance()
    });
  }
}
