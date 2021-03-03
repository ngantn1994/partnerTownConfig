

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;import javax.swing.JOptionPane;
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

public class Report2Ctrl {
	
	private static final String TITLE = "S\u1ef1 c\u1ed1";
	private static final String NOT_AVAIBLE = "Tên \u0111\u0103ng nh\u1EADp này không \u0111úng.";
	private static final String NOTICE_TITLE = "Thông báo";
	private static final String NOTICE = "Xu\u1EA5t Report thành công!";
	
	private static Report2Ctrl ctrl = new Report2Ctrl();
	
	public static Report2Ctrl getInstance(){
		return ctrl;
	}
	
	private static Report2 view;
	
	private ActionListener getAddActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserDAO.getInstance().checkAvaible(view.getUsername())){
					try {				
						JasperReport jasperReport = (JasperReport) JRLoader.loadObject("./bin/report/all_user_pets.jasper");
						
						JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(UserPetDAO.getInstance().getAllUserPet((view.getUsername()))));
						
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
				} else {
					JOptionPane.showMessageDialog(null, NOT_AVAIBLE, TITLE, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		return listener;
	}

	private ActionListener getBackActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View.changeContentPane(ReportCtrl.getContentPane());
			}
		};
		return listener;
	}
	
	private Report2Ctrl(){
		view = Report2.getInstance();
		view.setAddActionListener(getAddActionListener());
		view.setBackActionListener(getBackActionListener());
	}
	
	public static Container getContentPane(){
		return view.getContentPane();
	}

}
