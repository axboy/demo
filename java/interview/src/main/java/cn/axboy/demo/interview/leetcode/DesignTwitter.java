package cn.axboy.demo.interview.leetcode;

import java.util.*;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/14 15:05
 * https://leetcode-cn.com/problems/design-twitter/
 */
public class DesignTwitter {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        System.out.println(twitter.getNewsFeed(1));

        // 用户1关注了用户2.
        twitter.follow(1, 2);

        // 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

        // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        System.out.println(twitter.getNewsFeed(1));

        // 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        System.out.println(twitter.getNewsFeed(1));
    }
}

//用户
class TwitterUser {
    int userId;
    Set<Integer> followSet;     //包含自己

    TwitterUser(int u) {
        userId = u;
        followSet = new HashSet<>();
        followSet.add(userId);
    }

    void follow(int fUserId) {
        if (userId != fUserId) {
            followSet.add(fUserId);
        }
    }

    void unFollow(int fUserId) {
        if (userId != fUserId) {
            followSet.remove(fUserId);
        }
    }
}

//文章,逆序链表
class Tweet {
    static int global_timestamp = 0; //全局id

    int userId;
    int tweetId;
    int timestamp;
    Tweet next;

    //不考虑输入的tweetId重复
    Tweet(int u, int t) {
        userId = u;
        tweetId = t;
        timestamp = global_timestamp++;
    }
}

class Twitter {

    //用户
    Map<Integer, TwitterUser> userMap = new HashMap<>();

    //纪录用户最后一篇推特
    Map<Integer, Tweet> userTweetMap = new HashMap<>();

    //查询10条
    final int LIMIT = 10;

    public Twitter() {
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        TwitterUser user = userMap.get(userId);
        if (user == null) {
            user = new TwitterUser(userId);
            userMap.put(userId, user);
        }
        Tweet lastHead = userTweetMap.get(userId);
        Tweet newTweet = new Tweet(userId, tweetId);
        if (lastHead != null) {
            newTweet.next = lastHead;
        }
        userTweetMap.put(userId, newTweet);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        TwitterUser user = userMap.get(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        //优先队列
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((o1, o2) -> o2.timestamp - o1.timestamp);
        for (Integer it : user.followSet) {
            Tweet tweet = userTweetMap.get(it);
            if (tweet != null) {
                maxHeap.offer(tweet);
            }
        }
        List<Integer> result = new ArrayList<>(LIMIT);
        int count = 0;
        while (count++ < LIMIT && maxHeap.size() > 0) {
            Tweet head = maxHeap.poll();
            result.add(head.tweetId);
            if (head.next != null) {
                maxHeap.offer(head.next);
            }
        }
        return result;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        TwitterUser user = userMap.get(followerId);
        if (user == null) {
            user = new TwitterUser(followerId);
            userMap.put(followerId, user);
        }
        user.follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        TwitterUser user = userMap.get(followerId);
        if (user != null) {
            user.unFollow(followeeId);
        }
    }
}
