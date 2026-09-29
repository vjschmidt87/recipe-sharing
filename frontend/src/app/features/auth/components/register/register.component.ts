import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { TranslationService } from '../../../../core/services/translation.service';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  t = inject(TranslationService);
  private auth = inject(AuthService);
  private router = inject(Router);

  username = '';
  email = '';
  password = '';
  error = '';

  onSubmit(): void {
    this.error = '';
    this.auth.register(this.username, this.email, this.password).subscribe({
      next: () => this.router.navigate(['/']),
      error: () => this.error = this.t.t('common.error')
    });
  }
}
