import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../services/token-storage.service';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AppComponent } from '../app.component';
import { WarningOptions } from '../models/warning-options';
import { MatDialog } from '@angular/material/dialog';
import { WarningDialogComponent } from '../warning-dialog/warning-dialog.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: User;
  editingUser: User = new User;

  constructor(private token: TokenStorageService, private userService: UserService, public _snackBar: MatSnackBar, private appComponent: AppComponent, public dialog: MatDialog) { }

  ngOnInit() {
    if (this.token.getUser()) {
      this.userService.getUserByEmail(this.token.getUser().email).subscribe(x => {
        this.currentUser = x
        Object.assign(this.editingUser, this.currentUser);
      });
    }
  }

  editProfile() {
    if (JSON.stringify(this.currentUser) == JSON.stringify(this.editingUser)) {
      this.openSnackBar("Nem módosított adatot", "Értem")
    } else {
      Object.assign(this.currentUser, this.editingUser);
      this.userService.updateUser(this.currentUser).subscribe(x => {
        window.location.reload()
      });
    }
  }

  reloadPage(){
    window.location.reload()
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  openWarningDialog(warning: WarningOptions): void {
    const dialogRef = this.dialog.open(WarningDialogComponent, {
      width: '400px',
      data: warning
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openBuyDialog(warning: WarningOptions): void {
    const dialogRef = this.dialog.open(WarningDialogComponent, {
      width: '400px',
      data: warning
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == warning.optionYes) {
        this.currentUser.balance = this.currentUser.balance - 20
        this.userService.upgradeUser(this.currentUser).subscribe(x => {
          this.appComponent.refreshPremiumStatus()
          window.location.reload()
          this.openSnackBar("Sikeres vásárlás", "Értem")
        })
      }
    });
  }

  buyPremium(): void {
    if (this.currentUser.balance < 20) {
      const warning = new WarningOptions()
      warning.header = "Figyelmeztetés"
      warning.text = "A prémium tagság $20 -ba kerül, és felöldja az összes filmet, és a prémium oldalt. Töltsön fel az egyenlegére, mert nincsen elegendő pénze ehhez."
      warning.optionYes = "Rendben"
      warning.optionNo = ""
      this.openWarningDialog(warning)
    } else {
      const warning = new WarningOptions()
      warning.header = "Figyelmeztetés"
      warning.text = "Biztosan prémium tag akar lenni? Ez a szolgáltatás $20 -ba kerül, és feloldja az összes filmet és a prémium oldalt. A vásárlás után ennyi lesz az egyenlege: $" + (this.currentUser.balance - 20)
      warning.optionYes = "Igen, megveszem"
      warning.optionNo = "Mégsem"
      this.openBuyDialog(warning)
    }
  }

}