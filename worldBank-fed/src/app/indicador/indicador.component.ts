import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { MatPaginator,MatPaginatorModule } from '@angular/material/paginator';
import { IndicadorService } from '../core/service/indicador.service';
import { HttpClientModule } from '@angular/common/http'; 
import { Indicador } from './model/indicador.model';

@Component({
  selector: 'app-indicador',
  standalone: true,
  imports: [
    MatToolbarModule, 
    MatIconModule, 
    FormsModule,
    MatInputModule, 
    MatButtonModule,
    CommonModule,
    MatTableModule,
    MatPaginator, 
    HttpClientModule,
    MatPaginatorModule],
  templateUrl: './indicador.component.html',
  styleUrl: './indicador.component.scss'
})
export class IndicadorComponent implements OnInit ,AfterViewInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  dataSource = new MatTableDataSource<Indicador>;
  displayedColumnsData: string[] = ['pais', 'ano', 'indicador'];

  codigo: string = '';
  errorMessage: string = '';

 
  codigoInvalido: boolean = false;
  mostraTabela: boolean = false;

  constructor(private service: IndicadorService) {}
  ngOnInit(): void {
    this.mostraTabela = false;
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  onSubmit() {
    if (!this.codigo) {
      this.codigoInvalido = true;
    } else {
      this.service.getIndicador(this.codigo).subscribe(
        (response) => {
          if (response[0].message) {
            this.mostraTabela = false;
            this.errorMessage = 'Erro ao buscar dados na API. Verificar código do país.';
          } else {
            this.dataSource.data = this.mapResponseToIndicador(response);
            this.errorMessage = '';
            this.mostraTabela = true;
          }
        },
        (error) => {
          this.mostraTabela = false;
          this.errorMessage = 'Erro ao buscar dados na API. Verificar código do país.';
        }
      );
      this.codigoInvalido = false;
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

  openCountryCodes() {
    window.open(' http://api.worldbank.org/v2/country', '_blank');
  }
  
}
