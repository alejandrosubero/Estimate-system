import { Component, OnInit } from '@angular/core';
import { UserRecovery } from 'src/app/models/UserRecovery.model';

@Component({
  selector: 'app-loging-recuperar',
  templateUrl: './loging-recuperar.component.html',
  styleUrls: ['./loging-recuperar.component.css']
})
export class LogingRecuperarComponent implements OnInit {

  userRecovery: UserRecovery = new UserRecovery ();


  constructor() { }

  ngOnInit(): void {
  }

}
