import { Component } from '@angular/core';
import {MatToolbar} from "@angular/material/toolbar";

@Component({
  selector: 'app-banner',
  imports: [
    MatToolbar
  ],
  standalone: true,
  templateUrl: './banner.component.html',
  styleUrl: './banner.component.sass'
})
export class BannerComponent {

}
