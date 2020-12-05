import { Router } from '@angular/router';
import { RoomService } from './../room.service';
import { Room } from './../Room';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-client-room',
  templateUrl: './client-room.component.html',
  styleUrls: ['./client-room.component.css']
})
export class ClientRoomComponent implements OnInit {
  room:Room=new Room();
  subscribe=false;

  constructor(private roomService:RoomService,private router:Router) { }

  ngOnInit() {
  }

  newRoom():void{
    this.subscribe=false;
    this.room=new Room();

  }

  sava(){
    this.roomService.createRooms(this.room);
    
  }

}
