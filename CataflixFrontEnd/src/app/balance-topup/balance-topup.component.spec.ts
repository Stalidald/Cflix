import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BalanceTopupComponent } from './balance-topup.component';

describe('BalanceTopupComponent', () => {
  let component: BalanceTopupComponent;
  let fixture: ComponentFixture<BalanceTopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BalanceTopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BalanceTopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
