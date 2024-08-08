import { Component, ViewChild } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import {MatTableDataSource, MatTableModule} from '@angular/material/table'
import { MatPaginator } from '@angular/material/paginator';
import { IndicadorService } from '../core/service/indicador.service';
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { Indicador } from './model/indicador.model';

@Component({
  selector: 'app-indicador',
  standalone: true,
  imports: [MatToolbarModule, 
    MatIconModule, 
    FormsModule,MatInputModule, 
    MatButtonModule,
    CommonModule,
    MatTableModule,
    MatPaginator, 
    HttpClientModule],
  templateUrl: './indicador.component.html',
  styleUrl: './indicador.component.scss'
})
export class IndicadorComponent {
  codigo: string = '';
  codigoInvalido: boolean = false;
  data: Indicador[] = [];
  displayedData: any[] = [];
  displayedColumns: string[] = ['pais', 'data', 'indicador'];
  currentPage: number = 1;
  itemsPerPage: number = 5;
  Math: any;
  errorMessage: string = '';
  constructor(private service: IndicadorService) {}

  onSubmit() {
    if (!this.codigo) {
      this.codigoInvalido = true;
    } else {
      this.service.getIndicador(this.codigo).subscribe(
        (response) => {
          if (response[0].message) {
            this.errorMessage = 'Erro ao buscar dados na API. Verificar código do país.';
          } else {
            this.data = this.mapResponseToIndicador(response);
            this.updateDisplayedData();
            this.errorMessage = '';
          }
        },
        (error) => {
          this.errorMessage = 'Erro ao buscar dados na API. Verificar código do país.';
        }
      );
      this.codigoInvalido = false;
      this.updateDisplayedData();
    }
  }

  mapResponseToIndicador(response: any): Indicador[] {
    const data = response[1]; 
    return data.map((item: any) => ({
      pais: item.country.value,
      data: item.date,
      indicador: item.value
    }));
  }

  updateDisplayedData() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedData = this.data.slice(startIndex, endIndex);
  }

  changePage(page: number) {
    this.currentPage = page;
    this.updateDisplayedData();
  }

  totalPages(): number {
    return Math.ceil(this.data.length / this.itemsPerPage);
  }

  openCountryCodes() {
    window.open(' http://api.worldbank.org/v2/country', '_blank');
  }
  
}
