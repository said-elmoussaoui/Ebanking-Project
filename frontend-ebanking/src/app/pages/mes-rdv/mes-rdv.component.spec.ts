import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesRDVComponent } from './mes-rdv.component';

describe('MesRDVComponent', () => {
  let component: MesRDVComponent;
  let fixture: ComponentFixture<MesRDVComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MesRDVComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MesRDVComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
