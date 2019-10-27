/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Point;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.xmp.impl.Utils;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.FileNotFoundException;

/**
 *
 * @author leona
 */
public class Relatorio {
    static String dest = "d:\\users\\ricardo\\testepdf\\teste.pdf";
    private Document documento;
    private PdfDocument pdf;
    
    public int millimetrosParaPontos(double milimetros){
        double polegadas;
        double millimetrosPorPolegadas = 25.4;
        polegadas = milimetros/millimetrosPorPolegadas;
        return  (int) (polegadas * 72);
    }
    
    public void salvarPdf() throws FileNotFoundException{
        Table tabela = new Table(UnitValue.createPercentArray(new float[]{10,10,10,10,10,10,10,10,10,10})).setFixedLayout();
        tabela.setHeight(UnitValue.createPercentValue(100));
        tabela.addHeaderCell(new Cell(0, 10).add(new Paragraph("Uniovo")).setTextAlignment(TextAlignment.CENTER));

        tabela.addHeaderCell(new Cell(0, 7).add(new Paragraph(new Text("Relatório de Não Conformidade")))
                                                .setTextAlignment(TextAlignment.CENTER)
                                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                                .setHeight(40));
        tabela.addHeaderCell(new Cell(0, 3).add(new Paragraph(new Text("RNC nº:")))
                                                .setTextAlignment(TextAlignment.CENTER)
                                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                                                .setHeight(40));
        tabela.addCell(new Cell(0, 2).add(new Paragraph("Emitente: "))
                            .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                            .setTextAlignment(TextAlignment.RIGHT));
        tabela.addCell(new Cell(0, 8).add(new Paragraph("Nome do emitente")));

        tabela.useAllAvailableWidth();
        documento.add(tabela);documento.add(tabela);documento.add(tabela);
        documento.add(tabela);
        documento.close();
    }
    
    
    public void criarEAdicionarConteudo() throws FileNotFoundException{
        criarDocumento();
        criarPagina();
        salvarPdf();
    }
    
    public void criarPagina(){
        documento.add(new Paragraph(new Text("Relatório de Não Conformidades")).setTextAlignment(TextAlignment.CENTER));
        documento.add(new Paragraph(new Text("Gerado em:")));
        inserirQuebraDePagina();
    }
    
    public void inserirQuebraDePagina(){
        documento.add(new AreaBreak(PageSize.A4));
    }
    
    public void criarDocumento() throws FileNotFoundException{
        PdfWriter writer = new PdfWriter(dest);
        pdf = new PdfDocument(writer);
        documento = new Document(pdf, PageSize.A4);
        documento.setMargins(30, 20, 20, 30);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        System.out.println(new Relatorio().millimetrosParaPontos(21));
    }
}
