import java.util.*;

class Solution {
   public String[] solution(String[][] tickets) {

		int len = tickets.length;
		String[] answer = new String[len + 1];

		// DFS방문 기록 확인 배열
		boolean[] visited = new boolean[len];

		// 모든 경로를 담은 뒤, 정렬 할 배열
		ArrayList<String[]> ansList = new ArrayList<String[]>();

		// DFS 시작 - 시작점 : ICN
		dfs(visited, tickets, 0, "ICN", answer, ansList);
		
		if(ansList.size() == 1)
			return ansList.get(0);

		Comparator<String[]> comp = new Comparator<String[]>() {
			@Override
			public int compare(String[] arr1, String[] arr2) {
				String conStr1 = "", conStr2 = "";
				for (int i = 0; i < arr1.length; i++) {
					conStr1 += arr1[i];
					conStr2 += arr2[i];
				}
				return conStr1.compareTo(conStr2);
			}
		};
		// 알파벳 순 정렬
		ansList.sort(comp); 
		return ansList.get(0);
	}

	private void dfs(boolean[] visited, String[][] tickets, int index, String from, String[] answer,
			ArrayList<String[]> ansList) {
		// 모든 티켓 확인 완료 시, List에 배열 추가 - 다중 경로 확인될 경우 정렬을 위함
		if (index == tickets.length) {
			ansList.add(answer.clone());
			return;
		}

		for (int i = 0; i < visited.length; i++) {
			// 출발지가 일치하며 방문하지 않은 ticket일 경우,
			if (from.equals(tickets[i][0]) && !visited[i]) {
				visited[i] = true;
				// 출발지 입력
				answer[index] = from;
				// 도착지 입력
				answer[index + 1] = tickets[i][1];
				// 도착지를 출발지로, index를 추가하여 재귀 호출
				dfs(visited, tickets, index + 1, tickets[i][1], answer, ansList);
				visited[i] = false;
			}
		}

	}
}
