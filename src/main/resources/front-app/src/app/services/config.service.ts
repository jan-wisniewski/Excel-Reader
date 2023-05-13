import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {SharedService} from "./shared.service";

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor(private httpClient: HttpClient, private sharedService: SharedService) { }

  public getColumnNames(): Observable<HttpResponse<string[]>> {
    return this.httpClient.get<string[]>(`${this.sharedService.getUrl()}/api/config/columns`, { observe: 'response' })
  }

}
