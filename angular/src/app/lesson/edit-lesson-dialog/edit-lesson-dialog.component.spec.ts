import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLessonDialogComponent } from './edit-lesson-dialog.component';

describe('EditLessonDialogComponent', () => {
  let component: EditLessonDialogComponent;
  let fixture: ComponentFixture<EditLessonDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditLessonDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditLessonDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
