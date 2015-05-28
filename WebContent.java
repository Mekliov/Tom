package org.WebContent;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@WebServlet("/WebContent")
public class WebContent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,

	HttpServletResponse response) throws ServletException, IOException {
		try {
			URL url = new URL(
					"http://feeds.bbci.co.uk/news/world/rss.xml?edition=uk");
			HttpURLConnection httpcon = (HttpURLConnection) url
					.openConnection();
			// Reading the feed
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed;
			feed = input.build(new XmlReader(httpcon));
			List<?> entries = feed.getEntries();
			Iterator<?> itEntries = entries.iterator();
			StringBuilder str = new StringBuilder();
			PrintWriter writer = response.getWriter();

			while (itEntries.hasNext()) {
				SyndEntry entry = (SyndEntry) itEntries.next();
				str.append(entry.getTitle() + "\n");
				str.append(entry.getDescription().getValue() + "\n");
				writer.append(str.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
