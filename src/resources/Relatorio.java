/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import controller.Controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NaoConformidade;
import model.Usuario;

/**
 *
 * @author leona
 */
public class Relatorio {
    private Document documento;
    private PdfDocument pdf;
    private float margemDireita;
    private float margemSuperior;
    private float margemEsquerda;
    private float margemInferior;
    private final PageSize tamanhoPagina;

    public Relatorio() {
        this.tamanhoPagina = PageSize.A4;
    }

    public int millimetrosParaPontos(double milimetros){
        double polegadas;
        double millimetrosPorPolegadas = 25.4;
        polegadas = milimetros/millimetrosPorPolegadas;
        return  (int) (polegadas * 72);
    }
    
    public void listarNaoConformidade(NaoConformidade nc){
        documento.add(
                new Table(UnitValue.createPercentArray(new float[]{10,10,10,10,10,10,10,10,10,10})).
                setHeight(UnitValue.createPercentValue(100)).
                useAllAvailableWidth()
                .addCell(
                        new Cell(0,4).add(
                                new Paragraph("Código:")
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 3)
                                .add(
                                        new Paragraph(
                                                new Text("Data de acontecimento:")
                                        )
                                
                                )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 3)
                                .add(
                                        new Paragraph(
                                                new Text("Data de registro:")
                                        )
                                )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 4).add(
                                new Paragraph(
                                        new Text(String.format("%010d", nc.getId()))
                                )
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        )
                .addCell(
                        new Cell(0, 3).add(
                                new Paragraph(
                                        new Text(String.format(
                                                "%02d/%02d/%d",
                                                31,10,2019)
                                        )
                                )
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                )
                .addCell(
                        new Cell(0, 3).add(
                                new Paragraph(
                                        new Text(String.format(
                                                "%02d/%02d/%01d",
                                                01,11,2019)
                                        )
                                )
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                )
                .addCell(
                        new Cell(0, 10).add(
                                new Paragraph(
                                        new Text("Descrição")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 10).add(
                                new Paragraph(
                                        new Text(nc.getDescricao())
                                )
                        )
                )
                .addCell(
                        new Cell(0, 10).add(
                                new Paragraph(
                                        new Text("Abrangência")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 10).add(
                                new Paragraph(
                                        new Text(nc.getAbrangencia())
                                )
                        )
                )
                .addCell(
                        new Cell(0, 8).add(
                                new Paragraph(
                                        new Text("Origem")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 2).add(
                                new Paragraph(
                                        new Text("Reincidência")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 8).add(
                                new Paragraph(
                                        new Text(nc.getOrigem())
                                )
                        )
                )
                .addCell(
                        new Cell(0, 2).add(
                                new Paragraph(
                                        new Text(Resources.converterBooleanoSimOuNaoMaiusculo(nc.getReincidencia()))
                                )
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Paragraph(
                                        new Text("Setor")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Paragraph(
                                        new Text("Responsável pelo Setor")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Cell().add(
                                        new Paragraph(
                                            new Text(String.format("Código: %010d", nc.getSetor().getId()))
                                        )
                                )
                                .add(
                                        new Paragraph(
                                        new Text(String.format("Nome: %s",nc.getSetor().getNome()))
                                    )
                                )
                        )
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Cell().add(
                                        new Paragraph(
                                            new Text(String.format("Código: %010d", nc.getSetor().getResponsavel().getId()))
                                        )
                                )
                                .add(
                                        new Paragraph(
                                        new Text(String.format("Nome: %s",nc.getSetor().getResponsavel().getNome()))
                                    )
                                )
                                .add(
                                        new Paragraph(
                                        new Text(String.format("CPF: %s",nc.getSetor().getResponsavel().getCpf()))
                                    )
                                )
                        )
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Paragraph(
                                        new Text("Ação de Correção")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Paragraph(
                                        new Text("Responsável pelo Registro da NC")
                                )    
                        )
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Paragraph(
                                    new Text(nc.getAcaoCorrecao())
                                )
                        )
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                )
                .addCell(
                        new Cell(0, 5).add(
                                new Cell().add(
                                        new Paragraph(
                                            new Text(String.format("Código: %010d", nc.getResponsavel().getId()))
                                        )
                                )
                                .add(
                                        new Paragraph(
                                        new Text(String.format("Nome: %s",nc.getResponsavel().getNome()))
                                    )
                                )
                                .add(
                                        new Paragraph(
                                        new Text(String.format("CPF: %s",nc.getResponsavel().getCpf()))
                                    )
                                )
                        )
                )
                
        );
        documento.add(new Paragraph("\n"));
    }
    
    public void fecharDocumento(){
        documento.close();
    }
    
    public void colocarNumeroDePaginas(){
        int numeroPaginas = pdf.getNumberOfPages();
        for(int i=1; i <= numeroPaginas; i++){
            documento.showTextAligned(
                    new Paragraph(
                            String.format("Página %s de %s", i, numeroPaginas)
                    ), 
                    tamanhoPagina.getWidth()-documento.getRightMargin(), 
                    documento.getBottomMargin()-20,
                    i, 
                    TextAlignment.RIGHT, 
                    VerticalAlignment.BOTTOM,
                    0
            );
        }
    }
    
    public void criarPaginaInicial(Usuario emitente){
        documento.add(
                new Paragraph(
                        new Text("Relatório de Não Conformidades")
                )
                .setTextAlignment(TextAlignment.CENTER)
        );
        documento.add(
                new Paragraph(
                        new Text(
                                String.format("Relatório emitido em %s às %s", 
                                    Resources.getData(), 
                                    Resources.getHora())
                        )
                ));
        documento.add(
                new Paragraph(
                        new Text(
                                String.format("Emitente: %s CPF: %s", 
                                        emitente.getNome(), 
                                        emitente.getCpf())
                        )
                ));
        inserirQuebraDePagina();
    }
    
    public void inserirQuebraDePagina(){
        documento.add(new AreaBreak(PageSize.A4));
    }
    
    public void criarDocumento(String caminhoDestino){
        if(caminhoDestino!=null){
            PdfWriter writer;
            try {
                writer = new PdfWriter(caminhoDestino);
                pdf = new PdfDocument(writer);
                documento = new Document(pdf, tamanhoPagina, false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
    
    public void definirMargens(float esquerda, float superior, float direita, float inferior){
        documento.setMargins(
                millimetrosParaPontos(superior),
                millimetrosParaPontos(direita), 
                millimetrosParaPontos(inferior), 
                millimetrosParaPontos(esquerda)
        );
    }
    
}
