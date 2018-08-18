import {Injectable} from '@angular/core';
import {StatsRow} from '../common/StatsRow';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {environment} from "../../environments/environment";


@Injectable()
export class MemberService {

  private membersUrl = environment.serverUrl + '/stats/users';

  constructor(private http: HttpClient) {
  }

  getMembers(): Promise<StatsRow[]> {
    return this.http.get(this.membersUrl)
      .toPromise()
      .then(response => response as StatsRow[])
      .catch(this.handleError);
  }

  // getMember(id: number): Promise<Member> {
  //     return this.getMembers().then(members => members.find(member => member.id === id));
  // }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred accessing ' + environment.serverUrl, error);
    if (error instanceof HttpErrorResponse) {
      console.error("Response status: " + error.status + " | Message: " + error.message);
    }
    return Promise.reject(error.message || error);
  }
}
