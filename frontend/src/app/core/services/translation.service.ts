import { Injectable, signal } from '@angular/core';
import { en } from '../i18n/en';
import { ptBr } from '../i18n/pt-br';

export type Lang = 'en' | 'pt-br';

@Injectable({ providedIn: 'root' })
export class TranslationService {
  lang = signal<Lang>((localStorage.getItem('lang') as Lang) || 'en');

  private translations: Record<Lang, Record<string, any>> = { en, 'pt-br': ptBr };

  toggleLang(): void {
    const next: Lang = this.lang() === 'en' ? 'pt-br' : 'en';
    this.lang.set(next);
    localStorage.setItem('lang', next);
  }

  t(key: string, params?: Record<string, string>): string {
    const keys = key.split('.');
    let value: any = this.translations[this.lang()];
    for (const k of keys) {
      value = value?.[k];
    }
    if (typeof value !== 'string') return key;
    if (params) {
      return Object.entries(params).reduce(
        (acc, [k, v]) => acc.replace(`{{${k}}}`, v), value
      );
    }
    return value;
  }
}
