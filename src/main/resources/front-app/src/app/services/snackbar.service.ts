import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackBar } from '../snackbar/snackbar.component';

@Injectable({
  providedIn: 'root'
})
export class SnackBarService {

  private type = new BehaviorSubject({
    name: '',
    msg: ''
  });

  sharedType = this.type.asObservable();

  constructor(private snackBar: MatSnackBar) { }

  nextType(name: string, msg: string) {
    this.type.next({name, msg});
  }

  openSnackBar(type: string, style: string, message?: string) {
      if (!message) {
        message = '';
      }
     this.nextType(type, message);
      this.snackBar.openFromComponent(SnackBar, {
        duration: 3 * 1000,
        horizontalPosition: 'end',
        verticalPosition: 'top',
        panelClass: [style],
      });
    }

}
