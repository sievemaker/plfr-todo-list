import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {ReactiveFormsModule} from "@angular/forms";
import {BannerComponent} from "./banner/banner.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ReactiveFormsModule, BannerComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title = 'plfr-todo-list-fe';
}
