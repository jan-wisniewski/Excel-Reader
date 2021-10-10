import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SnackBarService {

  private type = new BehaviorSubject({
    name: '',
    msg: ''
  });

  sharedType = this.type.asObservable();

  constructor() { }

  nextType(name: string, msg: string) {
    this.type.next({name, msg});
  }

}
