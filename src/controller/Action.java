package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
	public abstract String getName();

	public abstract String perform(HttpServletRequest request);

	public static Map<String, Action> map = new HashMap<String, Action>();

	public static void add(Action action) {
		synchronized (map) {
			if (map.get(action.getName()) != null) {
				throw new AssertionError("Duplicates action name");
			}
			map.put(action.getName(), action);
			System.out.println(action.getName());
		}
	}

	public static String perform(String name, HttpServletRequest request) {
		Action action;
		synchronized (map) {
			action = map.get(name);
		}
		if (action == null) return null;
		return action.perform(request);
	}
}
