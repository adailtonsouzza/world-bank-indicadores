import { Routes } from '@angular/router';
import { IndicadorComponent } from './indicador/indicador.component';

export const routes: Routes = [
    { path: '', component: IndicadorComponent },
    { path: 'indicador', component: IndicadorComponent }
];
