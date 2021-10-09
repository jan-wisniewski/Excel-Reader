import { Policy } from '../shared/models/policy/policy.model';
import { HttpClient, HttpEvent, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private httpClient: HttpClient) { }

  HTTP_URL = 'http://localhost:8081/api/';

  private _refresh$ = new Subject<void>();

  get refresh$() {
    return this._refresh$;
  }

  findAll(): Observable<HttpResponse<Policy[]>> {
    return this.httpClient.get<Policy[]>(`${this.HTTP_URL}policy/all`, { observe: 'response' });
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<HttpEvent<any>>(`${this.HTTP_URL}policy/upload`, formData, { })
    .pipe(
      tap( () => {
        this._refresh$.next();
      })
    );
  }

}
