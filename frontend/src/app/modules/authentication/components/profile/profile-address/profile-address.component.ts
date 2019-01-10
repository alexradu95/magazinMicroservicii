import { Component, OnInit } from '@angular/core';
import { Address } from '../model/address';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  selector: 'app-profile-address',
  templateUrl: './profile-address.component.html',
  styleUrls: ['./profile-address.component.css']
})
export class ProfileAddressComponent implements OnInit {

 
  address: Address = new Address();
  submitted: boolean = false;
  success: boolean = false;
  failed: boolean = false;
  message: string = "";   

 
  constructor(private userProfileService: UserProfileService) { }


  ngOnInit() {
    this.userProfileService.getProfile().subscribe(
      (data) => {
        let address = JSON.parse(JSON.stringify(data)).address ;
        console.log(this.address);
        if(address != null) {
          this.address = address;
          console.log(this.address);
        }
      }, (error) => {
          console.log(error);
      }
    );
  }

  save() {
    this.address.addressType = 0;
    this.userProfileService.updateAddress(this.address)
      .subscribe((data) => {
        console.log(data);
        this.success = JSON.parse(JSON.stringify(data)).ok;
        this.message = JSON.parse(JSON.stringify(data)).message;
      }, (error) => {

        this.failed = !JSON.parse(JSON.stringify(error.error)).ok;
        error.error.errors.forEach(element => {
          this.message += element.defaultMessage + "<br/>";
        });
      });
      if(this.success){
        this.submitted = true;
      }
    
  }
 
  onSubmit() {
    //this.submitted = true;
    this.success = false;
    this.failed = false;
    this.message = "";
    this.save();
  }

}
