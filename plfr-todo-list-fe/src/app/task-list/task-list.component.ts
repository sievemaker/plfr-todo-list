import {Component, inject, OnInit} from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatHeaderCellDef, MatRowDef } from '@angular/material/table';
import { Task } from '../models/task.model';
import {TaskService} from "../task.service";
import {MatIcon} from "@angular/material/icon";
import {RouterLink} from "@angular/router";
import {MatButtonModule} from "@angular/material/button";

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [MatTableModule, MatButtonModule, MatHeaderCellDef, MatRowDef, MatIcon, RouterLink],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.sass']
})
export class TaskListComponent implements OnInit{
  displayedColumns: string[] = ['title', 'description', 'actions'];
  dataSource: Task[] = [];

  taskService = inject(TaskService);

  ngOnInit(): void {
      this.loadTasks();
  }

  loadTasks(): void {
    this.taskService.getTasks().subscribe({
        next: (tasks) => {
          this.dataSource = tasks;
        },
        error: (error) => {
          console.error('Error loading tasks', error);
        }
      }
    )
  }

  deleteTask(id: string) {
    this.taskService.deleteTask(id).subscribe({
        next: () => {
          this.loadTasks();
        },
        error: (error) => {
          console.error('Error deleting task', error);
        }
      }
    )
  }
}
