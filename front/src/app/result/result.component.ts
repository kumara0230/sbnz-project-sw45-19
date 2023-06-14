import { Component, OnInit } from '@angular/core';
import { ControllerService } from '../service/controller.service';
import { Hero } from '../model/Hero';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {NgFor, NgIf} from '@angular/common';
import {MatTableModule} from '@angular/material/table';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ResultComponent implements OnInit{

  constructor(private service: ControllerService) {}

  heroes!: Hero[];
  dataSource = this.heroes;
  columnsToDisplay = ['score', 'localized_name', 'role', 'rank_play', 'meta', 'player_skills'];
  columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
  expandedElement!: Hero | null;

  myTeam!: Hero[];
  oppositeTeam!: Hero[];
 
  ngOnInit(): void {
    this.service.getRecommendedHeroes().subscribe({
      next: (res: any) => {
        this.heroes = res;
        this.mapImages();
        console.log(this.heroes)
      },
      error: (err: any) => {
        console.log(err);
      }
    });

    this.service.getMyTeam().subscribe({
      next: (res: any) => {
        this.myTeam = res;
      },
      error: (err: any) => {
        console.log(err);
      }
    });

    this.service.getOppositeTeam().subscribe({
      next: (res: any) => {
        this.oppositeTeam = res;
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }

  mapImages() {
    this.heroes.forEach(element => {
        element.image = `assets/${element.name}.jpg`;
    });
    this.dataSource = this.heroes;
  }

}
