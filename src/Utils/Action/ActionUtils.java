package Utils.Action;

import Utils.TimelineCheckerObject;
import twitter4j.Status;
import twitter4j.Twitter;

public class ActionUtils {

	public static Actions[] getActions(TimelineCheckerObject checkerObject, Status status){

		Actions[] actionses;
		boolean fav = false, re = false;
		for (Actions act : checkerObject.getActions()) {
			if (act != null) {
				if (act.name().equalsIgnoreCase(Actions.FAVORITE.name())) {
					fav = true;

				} else if (act.name().equalsIgnoreCase(Actions.RETWEET.name())) {
					re = true;

				}
			}

		}
			actionses = new Actions[]{fav ? Actions.FAVORITE : null, re ? Actions.RETWEET : null};

			return actionses;
	}

	public static void performActions(TimelineCheckerObject timelineCheckerObject, Status status, Twitter twitter) throws  Exception{
		for (Actions act : timelineCheckerObject.getActions()) {
			if (act != null) {
				if (act.name().equalsIgnoreCase(Actions.FAVORITE.name())) {

					if (!status.isFavorited())
						twitter.createFavorite(status.getId());

				} else if (act.name().equalsIgnoreCase(Actions.RETWEET.name())) {

					if (!status.isRetweeted())
						twitter.retweetStatus(status.getId());

				}
			}
		}
	}

}
