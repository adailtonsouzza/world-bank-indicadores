import { IndicadorComponent } from './indicador.component';
import { IndicadorService } from '../core/service/indicador.service';
import { of, throwError } from 'rxjs';
import { TestBed, ComponentFixture } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 

describe('IndicadorComponent', () => {
  let component: IndicadorComponent;
  let fixture: ComponentFixture<IndicadorComponent>;
  let service: IndicadorService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        FormsModule,
        HttpClientTestingModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatInputModule,
        CommonModule,
        MatTableModule,
        MatPaginatorModule,
        BrowserAnimationsModule,
        IndicadorComponent 
      ],
      providers: [IndicadorService]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndicadorComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(IndicadorService);
    fixture.detectChanges();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndicadorComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(IndicadorService);
    fixture.detectChanges();
  });

  it('deve criar o componente', () => {
    expect(component).toBeTruthy();
  });

  it('deve inicializar com a tabela oculta', () => {
    expect(component.mostraTabela).toBeFalse();
  });

  it('deve definir o paginador após a visualização', () => {
    component.ngAfterViewInit();
    expect(component.dataSource.paginator).toBe(component.paginator);
  });

  it('deve exibir mensagem de erro se o código estiver vazio', () => {
    component.codigo = '';
    component.onSubmit();
    expect(component.codigoInvalido).toBeTrue();
  });

  it('deve buscar dados e preencher a tabela ao submeter um código válido', () => {
    const mockResponse = [
      {},
      [
        { country: { value: 'Brasil' }, date: '2021', value: 100 },
        { country: { value: 'Brasil' }, date: '2020', value: 90 }
      ]
    ];
    spyOn(service, 'getIndicador').and.returnValue(of(mockResponse));
    component.codigo = 'BRA';
    component.onSubmit();
    expect(component.mostraTabela).toBeTrue();
    expect(component.dataSource.data.length).toBe(2);
  });

  it('deve exibir mensagem de erro ao receber resposta de erro da API', () => {
    const mockErrorResponse = [{ message: 'Erro' }];
    spyOn(service, 'getIndicador').and.returnValue(of(mockErrorResponse));
    component.codigo = 'BRA';
    component.onSubmit();
    expect(component.mostraTabela).toBeFalse();
    expect(component.errorMessage).toBe('Erro ao buscar dados na API. Verificar código do país.');
  });

  it('deve exibir mensagem de erro ao ocorrer um erro na requisição', () => {
    spyOn(service, 'getIndicador').and.returnValue(throwError('Erro'));
    component.codigo = 'BRA';
    component.onSubmit();
    expect(component.mostraTabela).toBeFalse();
    expect(component.errorMessage).toBe('Erro ao buscar dados na API. Verificar código do país.');
  });
});