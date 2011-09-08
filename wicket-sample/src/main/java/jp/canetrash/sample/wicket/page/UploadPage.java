package jp.canetrash.sample.wicket.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.lang.Bytes;

/**
 * @author tfunato
 * 
 */
public class UploadPage extends WebPage {

	/**
	 * Form for uploads.
	 */
	private class FileUploadForm extends Form<Void> {
		private static final long serialVersionUID = 1L;

		private FileUploadField fileUploadField;

		/**
		 * Construct.
		 * 
		 * @param name
		 *            Component name
		 */
		public FileUploadForm(String name) {
			super(name);

			// set this form to multipart mode (allways needed for uploads!)
			setMultiPart(true);

			// Add one file input field
			add(fileUploadField = new FileUploadField("fileInput"));

			// Set maximum size to 100K for demo purposes
			setMaxSize(Bytes.kilobytes(2048));
		}

		/**
		 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
		 */
		@Override
		protected void onSubmit() {
			System.out.println("################### Submitted!");
			final FileUpload upload = fileUploadField.getFileUpload();
			if (upload != null) {
				// Create a new file
				File newFile = new File(getUploadFolder(),
						upload.getClientFileName());

				// Check new file, delete if it already existed
				checkFileExists(newFile);
				try {
					// Save to new file
					newFile.createNewFile();
					upload.writeTo(newFile);

					UploadPage.this.info("saved file: "
							+ upload.getClientFileName());
				} catch (Exception e) {
					throw new IllegalStateException("Unable to write file", e);
				}
			}
		}
	}
	   /**
     * Constructor.
     * 
     * @param parameters
     *            Page parameters
     */
    public UploadPage(final PageParameters parameters)
    {
        // Add simple upload form, which is hooked up to its feedback panel by
        // virtue of that panel being nested in the form.
        final FileUploadForm simpleUploadForm = new FileUploadForm("simpleUpload");
        add(simpleUploadForm);
		System.out.println("This Page Called!!");
    }

	/**
	 * @return
	 */
	private String getUploadFolder() {
		WebApplication app = WebApplication.get();
		return app.getServletContext().getRealPath("/upload");
	}

	/**
	 * @param newFile
	 */
	private void checkFileExists(File newFile) {
		if (newFile.exists()) {
			if (!Files.remove(newFile)) {
				throw new IllegalStateException("Unable to overwrite "
						+ newFile.getAbsolutePath());
			}
		}
	}
}
