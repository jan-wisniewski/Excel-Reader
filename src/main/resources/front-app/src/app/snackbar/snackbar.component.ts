import { SnackBarService } from '../services/snackbar.service';
import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';
;import { faCheckCircle } from '@fortawesome/free-solid-svg-icons'
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-snackbar',
  template: `
    <div><fa-icon [icon]="icon"></fa-icon> {{ text }}</div>
<mat-progress-bar mode="determinate" value="{{value}}"></mat-progress-bar>
`
})
export class SnackBar implements OnInit {

  icon: any;
  text: string = '';
  value: number = 100;

  constructor(private snackBarService: SnackBarService) { }

  ngOnInit(): void {
    this.setProperties();
    const timer$ = interval(500);
    const subscribe = timer$.subscribe(second => this.value = 0)
  }

  setProperties(): void {
    this.snackBarService.sharedType.subscribe(type => {
      if (type && type.name) {
        switch(type.name) {
          case 'success': {
            this.text = type.msg;
            this.icon = faCheckCircle;
            break;
          }
          default: {
            this.text = type.msg;
            this.icon = faTimesCircle;
          }
        }
      }
    }
    )
  }

}
