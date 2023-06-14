import {Component, OnInit} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule, FormGroup} from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { ControllerService } from '../service/controller.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  myControl = new FormControl('');
  options: string[] = [];
  filteredOptions: Observable<string[]> | any;
  showOptions = false;
  formGroup!: FormGroup;
  selectedValue: string | any;

  firstHero: string | any;
  secondHero: string | any;
  thirdHero: string | any;
  fourthHero: string | any;
  fifthHero: string | any;

  myTeam: string[] = [];
  oppositeTeam: string[] = [];

  constructor(private service: ControllerService, private router: Router)  {};

  slideToggleValue: boolean = false;
  rank: string = "LOW";

  ngOnInit() {
    this.service.getAllHeroNames().subscribe({
      next: (res: any) => {
        this.options = res;
      },
      error: (err: any) => {
        console.log(err);
      }
    });



    this.formGroup = new FormGroup({
      firstName: new FormControl()
    });


    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  firstSelected(event: MatAutocompleteSelectedEvent) {
    this.firstHero = event.option.value;
    console.log(this.firstHero);
  }

  secondSelected(event: MatAutocompleteSelectedEvent) {
    this.secondHero = event.option.value;
    console.log(this.secondHero);
  }

  thirdSelected(event: MatAutocompleteSelectedEvent) {
    this.thirdHero = event.option.value;
    console.log(this.thirdHero);
  }

  fourthSelected(event: MatAutocompleteSelectedEvent) {
    this.fourthHero = event.option.value;
    console.log(this.fourthHero);
  }

  fifthSelected(event: MatAutocompleteSelectedEvent) {
    this.fifthHero = event.option.value;
    console.log(this.fifthHero);
  }

  myTeamButton() {
    console.log(this.fifthHero);
    if (this.firstHero != undefined) {
      this.myTeam.push(this.firstHero);
    }

    if (this.secondHero != undefined) {
      this.myTeam.push(this.secondHero);
    }

    if (this.thirdHero != undefined) {
      this.myTeam.push(this.thirdHero);
    }

    if (this.fourthHero != undefined) {
      this.myTeam.push(this.fourthHero);
    }

    if (this.fifthHero != undefined) {
      this.myTeam.push(this.fifthHero);
    }

    this.options = this.options.filter(option => !this.myTeam.includes(option));
    this.firstHero = undefined;
    this.secondHero = undefined;
    this.thirdHero = undefined;
    this.fourthHero = undefined;
    this.fifthHero = undefined;
  }

  oppositeButton() {
    console.log(this.fifthHero);
    if (this.firstHero != undefined) {
      this.oppositeTeam.push(this.firstHero);
    }

    if (this.secondHero != undefined) {
      this.oppositeTeam.push(this.secondHero);
    }

    if (this.thirdHero != undefined) {
      this.oppositeTeam.push(this.thirdHero);
    }

    if (this.fourthHero != undefined) {
      this.oppositeTeam.push(this.fourthHero);
    }

    if (this.fifthHero != undefined) {
      this.oppositeTeam.push(this.fifthHero);
    }

    this.service.sendRequest(this.myTeam, this.oppositeTeam, this.rank).subscribe({
      next: (res: any) => {
        console.log(res);
        this.router.navigate(['/result']);
      },
      error: (err: any) => {
        console.log(err);
      }
    });

    console.log(this.oppositeTeam)
  }

  onSlideToggleChange() {
    if (this.slideToggleValue) {
      this.rank = "HIGH";
    }
    else {
      this.rank = "LOW";
    }
    console.log('Slide toggle value:', this.rank);
    // Perform any other actions based on the slide toggle value
  }

}
