import { Component, inject, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslationService } from '../../../../core/services/translation.service';
import { RecipeService } from '../../../../core/services/recipe.service';

@Component({
  selector: 'app-add-edit-recipe',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-edit-recipe.component.html',
  styleUrl: './add-edit-recipe.component.scss'
})
export class AddEditRecipeComponent implements OnInit {
  t = inject(TranslationService);
  private fb = inject(FormBuilder);
  private recipeService = inject(RecipeService);
  private route = inject(ActivatedRoute);
  router = inject(Router);

  form!: FormGroup;
  isEdit = false;
  recipeId: number | null = null;
  cuisines = ['ITALIAN', 'MEXICAN', 'ASIAN', 'AMERICAN', 'BRAZILIAN', 'MEDITERRANEAN', 'INDIAN', 'FRENCH', 'OTHER'];
  dietaryTags = ['VEGETARIAN', 'VEGAN', 'GLUTEN_FREE', 'DAIRY_FREE', 'NUT_FREE', 'LOW_CARB', 'HIGH_PROTEIN', 'NONE'];

  ngOnInit(): void {
    this.form = this.fb.group({
      title: ['', Validators.required],
      titlePt: [''],
      description: ['', Validators.required],
      descriptionPt: [''],
      ingredients: ['', Validators.required],
      ingredientsPt: [''],
      instructions: ['', Validators.required],
      instructionsPt: [''],
      prepTimeMinutes: [15, [Validators.required, Validators.min(0)]],
      cookTimeMinutes: [30, [Validators.required, Validators.min(0)]],
      servings: [4, [Validators.required, Validators.min(1)]],
      cuisine: ['OTHER', Validators.required],
      dietaryTags: ['NONE'],
      imageUrl: ['']
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.recipeId = Number(id);
      this.recipeService.findById(this.recipeId).subscribe(r => {
        this.form.patchValue(r);
      });
    }
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    const data = this.form.value;
    const op = this.isEdit
      ? this.recipeService.update(this.recipeId!, data)
      : this.recipeService.create(data);
    op.subscribe(r => this.router.navigate(['/recipes', r.id]));
  }
}
