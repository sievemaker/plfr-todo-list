import { Routes } from '@angular/router';
import {TaskListComponent} from "./task-list/task-list.component";
import {SubmitTaskComponent} from "./submit-task/submit-task.component";

export const routes: Routes = [
  { path: '', component: TaskListComponent },
  { path: 'submit-task', component: SubmitTaskComponent },
];
