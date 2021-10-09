import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';
import { faCheckCircle } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-snackbar',
  template: `
    <div><fa-icon [icon]="successIcon"></fa-icon> {{ text }}</div>
<mat-progress-bar mode="determinate" value="{{value}}"></mat-progress-bar>
`
})
export class SnackbarComponent implements OnInit {

  successIcon = faCheckCircle;
  text: string = 'Dane zostały przesłane poprawnie';
  value: number = 100;

  constructor() { }

  ngOnInit(): void {
    const timer$ = interval(500);
    const subscribe = timer$.subscribe(second => this.value = 0)
  }

}
