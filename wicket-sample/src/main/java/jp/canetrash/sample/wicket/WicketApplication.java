package jp.canetrash.sample.wicket;

import jp.canetrash.sample.wicket.page.FileListPage;
import jp.canetrash.sample.wicket.page.LoginPage;
import jp.canetrash.sample.wicket.page.UploadPage;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author tfunato
 * 
 */
public class WicketApplication extends WebApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.protocol.http.WebApplication#init() ページのブックマーク？
	 */
	@Override
	protected void init() {
		super.init();
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getMarkupSettings().setStripWicketTags(true); // wicket:idタグを消すための設定

		mountBookmarkablePage("/login", LoginPage.class);
		mountBookmarkablePage("/filelist", FileListPage.class);
		mountBookmarkablePage("/uploader", UploadPage.class);
	}

	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class getHomePage() {
		return FileListPage.class;
	}

}
