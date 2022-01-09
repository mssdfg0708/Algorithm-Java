package Programmers;

import java.util.*;

public class SkillTree {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int skill_index = 0;

        ArrayList<String> skill_order = new ArrayList<>();
        for(int i=0; i<skill.length(); i++) {
            skill_order.add(skill.substring(i, i+1));
        }

        for (String skill_tree : skill_trees) {
            for (int j = 0; j < skill_tree.length(); j++) {
                String x = skill_tree.substring(j, j + 1);

                // 포함 안되어 있으면 통과
                if (!skill_order.contains(x)) {
                    if (j == skill_tree.length() - 1) {
                        answer++;
                        break;
                    }
                    continue;
                }

                // 포함 되어 있으면 순서 체크
                if (skill_order.get(skill_index).equals(x)) { // 순서가 맞다면
                    if (j == skill_tree.length() - 1) {
                        answer++;
                        break;
                    }
                    skill_index++;
                } else { // 순서가 틀리다면
                    break;
                }
            }
            skill_index = 0; // 0으로 초기화
        }

        return answer;
    }
}

class SkillTreeMain {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_tree = {"BACDE", "CBADF", "AECB", "BDA"};

        SkillTree skillTree = new SkillTree();
        int answer = skillTree.solution(skill, skill_tree);
        System.out.println(answer);
    }
}