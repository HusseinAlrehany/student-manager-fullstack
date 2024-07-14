import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StudentService } from './student.service';
import { Student } from './student';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import {FormControl} from '@angular/forms';
//import { catchError, tap } from 'rxjs/operators';
//import { throwError } from 'rxjs';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule,FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

//we have the service set and now ready to use here in component
//by injecting it to the constructoe of theat component
export class AppComponent implements OnInit{
   
  //declaring a variable to hold the students comming back from the backend
  //through the created service
  public students: Student[] = [];

  //creating a variable to hold the student passed to th eonOpenModel
  public editStudent: any;

  public deleteStudent!: any;

  //new piece of code 
  studentForm!: FormGroup;

  errorMessage!: string;

  successMessage!: string;
  //injecting the student service to the constructor of the component
  constructor (private fb: FormBuilder, private studentService: StudentService){
    //new piece of code 
     this.studentForm = this.fb.group({
          firstName: ['', Validators.required],
          phone: ['', Validators.required]
     });
  }

   //to call getStudents() defined below wherever that component is initialized
   //if the getStudents() gets a response from the service
   //it will set the students
   //if not it will show an error
  ngOnInit(): void {
    this.getStudents();
    this.editStudent = {};
    this.deleteStudent = {};
  
    
  }

  //new piece of code 
  form =  new FormGroup({
    firstName: new FormControl('',Validators.required)
})
  //new piece of code   
get firstName()
{
    return this.form.controls;
}



 


  //defing a function to get the students from the backend
  public getStudents(): void{
       this.studentService.getStudents().subscribe(
           //if we get a response which will be student array
           (response: Student[]) =>{
             //setting the students array defined on the top
             //to be equal to the body of the request(response)
             this.students = response;
           },
           //in case of an error it will be HTTP error
           (error: HttpErrorResponse)=>{
            alert(error.message);

           }
      
       );
  }



    //a method to add student by calling the service method
    public onAddStudent(addForm: NgForm): void{
          

         //get access to the add form in the html
         document.getElementById('add-student-form')?.click();

          this.studentService.addStudent(addForm.value).subscribe(
            (response: Student)=>{
                  console.log(response);
                  this.getStudents();
                  addForm.reset();
            }
               ,
               (error: HttpErrorResponse)=>{
                  alert(error.error.message);
                  addForm.reset();
               }

         );  
    }

    public onUpdateStudent(student: Student): void{
         this.studentService.updateStudent(student).subscribe(
              (response: Student)=>{
                console.log(response);
                this.getStudents();

              },
              (error: HttpErrorResponse)=>{
                   alert(error.message);   
              }
         );   
    }

    public onDeleteStudent(studentId: number): void{
         this.studentService.deletStudent(studentId).subscribe(
              (response: void)=> {
                   console.log(response);
                   this.getStudents();
              },
              (error: HttpErrorResponse) => {
                  alert(error.message);
              }
         );
    }

    //to search a student
    public searchStudent(key: string):  void{
      console.log(key);
      const results: Student[] = [];
      for(const student of this.students){
        if(student.firstName.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || (student.phone.indexOf(key)) !== -1
        || (student.course.toLowerCase().indexOf(key.toLowerCase())) !== -1
        )
        results.push(student);
        }

        this.students = results;
        if(results.length === 0 || !key){
              this.getStudents();
        }
      }

    

   /* public onUpdateStudent(student: Student): void {
      this.studentService.updateStudent(student)
        .pipe(
          tap((response: Student) => {
            console.log(response);
            this.getStudents();
          }),
          catchError((error: HttpErrorResponse) => {
            alert(error.message);
            return throwError(error);
          })
        )
        .subscribe();
    }*/

     //a method to trigger the action of the user
     //it detects what action a user want to do(add, update, delete)

     public onOpenModel(student: Student | null, mode: string): void{
           //get access ti the main container in .html
           const container = document.getElementById('main-container');
           //create the button
           const button = document.createElement('button');
           //the button by default when created it will be of type submit
          //since we did not want this button to submit data
          //we have to change the type to button
          button.type = 'button';
          //hiding the created button from ui
          button.style.display = 'none';
          //adding the data.toggle and modal to the button
          button.setAttribute('data-toggle', 'modal');
          //detect which button is clickd by the user
          //sinsce we are referncing an id we have to put #

          if(mode === 'add'){
            button.setAttribute('data-target', '#addStudentModal');
          }
          if(mode === 'edit'){
            this.editStudent = student as Student;
            button.setAttribute('data-target', '#updateStudentModal');
          }

          if(mode === 'delete'){
            this.deleteStudent = student as Student;
            button.setAttribute('data-target', '#deleteStudentModal');
          }

          //append the button to the html
          container?.appendChild(button);
          //now the button is created and has all attributes on it
          //when it clicked it will open the appropriate modal
          button.click();

     }

  

     
}
