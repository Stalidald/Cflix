import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PremiumBoardComponent } from './premium-board.component';

describe('PremiumBoardComponent', () => {
  let component: PremiumBoardComponent;
  let fixture: ComponentFixture<PremiumBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PremiumBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PremiumBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
