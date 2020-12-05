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
  submitted=false;

  constructor(private roomService:RoomService,private router:Router) { }

  ngOnInit() {
  }

  newRoom():void{
    this.submitted=false;
    this.room=new Room();

  }

  save(){
    this.roomService.createRooms(this.room)
    .subscribe(
      data=>console.log(data),
      error=>console.log(error)  
    )
    this.room=new Room();
    this.goToList();

  }

  onSubmit(){
    this.submitted=true;
    this.save();
  }

  getList(){
    this.router.navigate(["/rooms"])
  }

}
