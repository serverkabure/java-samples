package jp.canetrash.sample.wicket;

import jp.canetrash.sample.wicket.page.FileListPage;
import junit.framework.TestCase;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester();
	}

	@Test
	public void testRenderMyPage()
	{
		//start and render the test page
		tester.startPage(FileListPage.class);

		//assert rendered page class
		tester.assertRenderedPage(FileListPage.class);

		//assert rendered label component
		tester.assertLabel("message", "If you see this message wicket is properly configured and running");
	}
}
