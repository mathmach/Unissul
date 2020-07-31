package mercado.model;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class JasperSoft {

    public static void generateReport(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        Connection conn = ConnectionManager.getInstance().getConnection();

        ServletContext context = request.getSession().getServletContext();
        String jrxml = context.getRealPath("/WEB-INF/report/report.jrxml");

        try {

            // compila jrxml em um arquivo .jasper
            String jasper = JasperCompileManager.compileReportToFile(jrxml);
            OutputStream output = response.getOutputStream();

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, null, conn);

            // exporta para pdf
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(conf);
            exporter.exportReport();

            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            e.printStackTrace();
        }

    }

}
