package jp.canetrash.sample.wicket.page;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.canetrash.sample.wicket.dto.UploadedFile;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.OddEvenListItem;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Login
 */
public class FileListPage extends WebPage {

	private static final long serialVersionUID = 1L;

	/**
	 * @param parameters
	 */
	public FileListPage(final PageParameters parameters) {
		WebApplication app = WebApplication.get();
		File[] files = new File(app.getServletContext().getRealPath("/upload"))
				.listFiles();
		IOFileFilter jpgFilter = FileFilterUtils.suffixFileFilter(".jpg",
				IOCase.INSENSITIVE);
		IOFileFilter gifFilter = FileFilterUtils.suffixFileFilter(".gif",
				IOCase.INSENSITIVE);
		IOFileFilter pngFilter = FileFilterUtils.suffixFileFilter(".png",
				IOCase.INSENSITIVE);

		files = FileFilterUtils.filter(
				FileFilterUtils.or(jpgFilter, gifFilter, pngFilter), files);
		List<UploadedFile> upFiles = new ArrayList<UploadedFile>();
		for (File file : files) {
			UploadedFile upFile = new UploadedFile();
			upFile.setName(file.getName());
			upFile.setLastModified(new Date(file.lastModified()));
			upFile.setSize(file.length());
			upFiles.add(upFile);
		}
		add(new ListView<UploadedFile>("filelist", upFiles) {
			private static final long serialVersionUID = 1L;
			final SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy/MM/dd hh:mm:ss");

			@Override
			protected void populateItem(ListItem<UploadedFile> item) {
				final UploadedFile file = item.getModelObject();
				Model<String> imageModel = new Model<String>("upload/"
						+ file.getName());
				ContextImage image = new ContextImage("file", imageModel);
				image.add(new SimpleAttributeModifier("width", "72"));
				image.add(new SimpleAttributeModifier("alt", ""));
				item.add(new Label("size", Long.toString(file.getSize() / 1024)
						+ "Kbyte"));
				item.add(new Label("lastModified", sdf.format(file
						.getLastModified())));
				ExternalLink link = new ExternalLink("anchor", imageModel);
				link.add(new SimpleAttributeModifier("title", file.getName()));
				item.add(link);
				link.add(image);
			}

			@Override
			protected ListItem<UploadedFile> newItem(int index) {
				return new OddEvenListItem<UploadedFile>(index,
						getListItemModel(getModel(), index)) {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onComponentTag(ComponentTag tag) {
						super.onComponentTag(tag);
						tag.put("class", (getIndex() % 2 == 0) ? ""
								: "alternate-row");
					}
				};
			}
		});
	}
}
