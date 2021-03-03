

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportCtrl {
	
private static ReportCtrl ctrl = new ReportCtrl();

	private static final String NOTICE_TITLE = "Thông báo";
	private static final String NOTICE = "Xu\u1EA5t Report thành công!";

	private List<User> users = UserDAO.getInstance().getAllUsers();
	
	public static ReportCtrl getInstance(){
		return ctrl;
	}
	
	private static Report view;
	
	private ActionListener getB5ActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {				
					JasperReport jasperReport = (JasperReport) JRLoader.loadObject("./bin/report/all_users.jasper");
					
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(users));
					
					// Export
					JRAbstractExporter exporter = new JRPdfExporter();

					// JRProperties.set
					if (exporter != null) {

						JFileChooser fileChooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF (*.pdf)", "pdf");
						fileChooser.setFileFilter(filter);
						if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

							exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "uft-8");
							exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
							exporter.setParameter(JRExporterParameter.OUTPUT_FILE, fileChooser.getSelectedFile());
							try {
								exporter.exportReport();
								JOptionPane.showMessageDialog(null, NOTICE, NOTICE_TITLE, JOptionPane.INFORMATION_MESSAGE);
							} catch (JRException er) {
								er.printStackTrace();
							}
						}
					}
				} catch (JRException er) {
					er.printStackTrace();
				}
			}
		};
		return listener;
	}

	private ActionListener getB6ActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View.changeContentPane(Report2Ctrl.getContentPane());
			}
		};
		return listener;
	}
	
	private ActionListener getBackActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View.changeContentPane(AllCtrl.getContentPane());
			}
		};
		return listener;
	}
	
	private ReportCtrl(){
		view = Report.getInstance();
		view.setB5ActionListener(getB5ActionListener());
		view.setB6ActionListener(getB6ActionListener());
		view.setBackActionListener(getBackActionListener());
	}
	
	public static Container getContentPane(){
		return view.getContentPane();
	}

}
