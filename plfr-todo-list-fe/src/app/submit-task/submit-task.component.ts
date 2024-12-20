import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatError, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {NgIf} from "@angular/common";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {TaskService} from "../task.service";
import {Task} from '../models/task.model';
import {Router, RouterLink} from "@angular/router";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-submit-task',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatLabel,
    MatError,
    ReactiveFormsModule,
    NgIf,
    MatIcon,
    RouterLink
  ],
  standalone: true,
  templateUrl: './submit-task.component.html',
  styleUrl: './submit-task.component.sass'
})
export class SubmitTaskComponent {
  fb = inject(FormBuilder);
  taskService = inject(TaskService);
  router = inject(Router);
  taskForm = this.fb.group({
    title: ['', [Validators.required, Validators.minLength(3)]],
    description: [''],
  });

  onSubmit() {
    if (this.taskForm.valid) {
      const task: Task = {
        title: this.taskForm.get('title')?.value ?? '',
        description: this.taskForm.get('description')?.value ?? '',
      };
      this.taskService.addTask(task).subscribe(({
        next: () => {
          this.router.navigate(['']);
        },
        error: (error) => {
          console.log('Error adding task:', error);
        }
      }))
    }
  }
}
