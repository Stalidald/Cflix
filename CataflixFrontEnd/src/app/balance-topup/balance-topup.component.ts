import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { ProfileComponent } from '../profile/profile.component';

@Component({
  selector: 'app-balance-topup',
  templateUrl: './balance-topup.component.html',
  styleUrls: ['./balance-topup.component.css']
})
export class BalanceTopupComponent implements OnInit {
  amount: number;

  user: User = new User()

  saveCredit: boolean = true;

  errorMessage: string = null

  constructor(public dialogRef: MatDialogRef<BalanceTopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User,
    public userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUserByEmail(this.data.email).subscribe(x => {
      this.user = x
      if(this.user.cvc==0){
        this.user.cvc=null
      }
    });
  }

  onSubmit() {
    if (this.user.creditCardNumber == null || this.user.cvc == 0 || this.user.expireDate == null || this.amount == null || this.amount == 0) {
      this.errorMessage = "Hiányos vagy helytelen kitöltés"
    } else {
      if (this.saveCredit) {
          this.user.balance = this.user.balance + this.amount
          this.userService.updateUser(this.user).subscribe(x => {
            this.dialogRef.close('topup')
          });
      } else {
        this.user.balance = this.user.balance + this.amount
        this.user.creditCardNumber = null
        this.user.cvc = 0
        this.user.expireDate = null
        this.userService.updateUser(this.user).subscribe(x => {
          this.dialogRef.close('topup')
        });
      }
    }
  }

}
