import { Policy } from '../shared/models/policy/policy.model';
import { HttpClient, HttpEvent, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import {SharedService} from "./shared.service";

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private httpClient: HttpClient, private sharedService: SharedService) { }

  private _refresh$ = new Subject<void>();

  get refresh$() {
    return this._refresh$;
  }

  public findAll(): Observable<HttpResponse<Policy[]>> {
    return this.httpClient.get<Policy[]>(`${this.sharedService.getUrl()}/api/policy/all`, { observe: 'response' })
  }

  deleteAll(): Observable<HttpResponse<Policy[]>> {
    return this.httpClient.delete<Policy[]>(`${this.sharedService.getUrl()}/api/policy/delete`, { observe: 'response' })
    .pipe(
          tap( () => {
            this._refresh$.next();
          })
        );
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<HttpEvent<any>>(`${this.sharedService.getUrl()}/api/policy/upload`, formData, { })
    .pipe(
      tap( () => {
        this._refresh$.next();
      })
    );
  }

}
