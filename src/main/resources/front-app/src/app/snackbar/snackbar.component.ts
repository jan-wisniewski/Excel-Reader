import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';

@Component({
  selector: 'app-snackbar',
  template: `
  <span class="example-pizza-party">ssss
<mat-progress-bar mode="determinate" value="{{value}}"></mat-progress-bar>
</span>
`
})
export class SnackbarComponent implements OnInit {

  text: string = 'Dane zostały przesłane poprawnie';
  value: number = 100;

  constructor() { }

  ngOnInit(): void {
    const timer$ = interval(500);
    const subscribe = timer$.subscribe(second => this.value = 0)
  }

}
