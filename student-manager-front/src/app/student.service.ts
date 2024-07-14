import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Student } from './student';

//in this service we will provide implementations to do some operations
//to reach to the backend and do certain operations
//we have to import HttpClient of angular
//and once we have access to this http we will be able to 
//send request to the back end 

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiUrl ='http://localhost:8080';

  constructor(private http: HttpClient) { }

  //to get all the students from the back end
  //but we have to design the UI to know the type of data that will be returned from that request

  public getStudents(): Observable<Student[]> {
    
    return this.http.get<any>(`${this.apiUrl}/students/all`);
  }

  //to add a student
  public addStudent(student : Student): Observable<Student>{
       
    return this.http.post<Student>(`${this.apiUrl}/students/add`, student);
    
    
  } 

  //to edit a student
  public updateStudent(student: Student): Observable<Student>{
     return this.http.put<Student>(`${this.apiUrl}/students/update`, student);
  }

  //to delete a student
  public deletStudent(studentId: number): Observable<void>{
       return this.http.delete<void>(`${this.apiUrl}/students/delete/${studentId}`);
  }



}
