import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TranslationService } from '../../../../core/services/translation.service';

@Component({
  selector: 'app-contact-page',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './contact-page.component.html',
  styleUrl: './contact-page.component.scss'
})
export class ContactPageComponent {
  t = inject(TranslationService);

  name = '';
  email = '';
  subject = '';
  message = '';

  onSubmit(): void {
    const body = `Name: ${this.name}%0D%0AEmail: ${this.email}%0D%0A%0D%0A${this.message}`;
    window.location.href = `mailto:contact@recipesharing.com?subject=${encodeURIComponent(this.subject)}&body=${body}`;
  }
}
