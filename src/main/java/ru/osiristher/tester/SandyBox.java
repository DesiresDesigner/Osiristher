/**
 * Created by DesiresDesigner on 13.02.15.
 */
package ru.osiristher.tester;

import ru.osiristher.properties.Config;
import ru.osiristher.tester.codes.Language;

import java.io.IOException;
import java.util.LinkedList;

public class SandyBox {

    public static void main(String args[ ]) throws Exception {

        LinkedList<String> resultsList = new LinkedList<String>();
        testHandler handler = new testHandler();
        handler.setResultsList(resultsList);

        String incorrectSource = "#include <iostream>\n" +
                "#include <string>\n" +
                "#include <cstdio>\n" +
                "#include <algorithm>  \n" +
                "\n" +
                "using namespace std;\n" +
                "\n" +
                "int getNearestForL(long *arr, int n, long k){\n" +
                "    int start = 0;\n" +
                "    int end = n - 1;\n" +
                "    while (true){\n" +
                "\n" +
                "        int center = (end+start)/2;\n" +
                "        if (end < start){\n" +
                "            return -1;\n" +
                "        }\n" +
                "        if (arr[center] >= k){\n" +
                "            if (center - 1 <= 0 || arr[center - 1] < k)\n" +
                "                return center;\n" +
                "            end = center - 1;\n" +
                "        }\n" +
                "        else if (arr[center] < k){\n" +
                "            if (center + 1 == n)\n" +
                "                return -1;\n" +
                "            if (arr[center + 1] >= k){\n" +
                "                return center + 1;\n" +
                "            }\n" +
                "            start = center + 1;\n" +
                "        }\n" +
                "    }\n" +
                "    return -1;\n" +
                "}\n" +
                "\n" +
                "int getNearestForR(long *arr, int n, long k){\n" +
                "    int start = 0;\n" +
                "    int end = n - 1;\n" +
                "    while (true){\n" +
                "\n" +
                "        int center = (end+start)/2;\n" +
                "        if (end < start){\n" +
                "            return -1;\n" +
                "        }\n" +
                "        if (arr[center] <= k){\n" +
                "            if (center + 1 == n || arr[center + 1] > k)\n" +
                "                return center;\n" +
                "            start = center + 1;\n" +
                "        }\n" +
                "        else if (arr[center] > k){\n" +
                "            if (center - 1 < 0)\n" +
                "                return -1;\n" +
                "            if (arr[center - 1] <= k){\n" +
                "                return center - 1;\n" +
                "            }\n" +
                "            end = center - 1;\n" +
                "        }\n" +
                "    }\n" +
                "    return -1;\n" +
                "}\n" +
                "\n" +
                "int main(void)\n" +
                "{\n" +
                "\tios_base::sync_with_stdio(false);\n" +
                "\tcin.tie(NULL);\n" +
                "\n" +
                "    int n, k;\n" +
                "    cin >> n >> k;\n" +
                "    long *starts = new long[n];\n" +
                "    long *ends = new long[n];\n" +
                "\n" +
                "    \n" +
                "    for (int i = 0; i < n; i++){\n" +
                "        int e1, e2;\n" +
                "        cin >> e1 >> e2;\n" +
                "        if (e1 <= e2){\n" +
                "            starts[i] = e1;\n" +
                "            ends[i] = e2;\n" +
                "        } else {\n" +
                "            starts[i] = e2;\n" +
                "            ends[i] = e1;\n" +
                "        }\n" +
                "    }    \n" +
                "\n" +
                "    sort(starts, starts + n);\n" +
                "    sort(ends, ends + n);\n" +
                "\n" +
                "    string result = \"\";\n" +
                "    for (int i = 0; i < k; i++){\n" +
                "        long el;\n" +
                "        cin >> el;\n" +
                "\n" +
                "        long starts_before = getNearestForR(starts, n, el);\n" +
                "        if (starts_before == -1)\n" +
                "            result += \"0 \";\n" +
                "        else{\n" +
                "            long ends_before = getNearestForR(ends, n, el - 1);\n" +
                "            result += to_string(starts_before - ends_before) + \" \";\n" +
                "        }\n" +
                "        \n" +
                "    }\n" +
                "    cout << 1;\n" +
                "\n" +
                "\treturn 0;\n" +
                "}";

        String source = "#include <iostream>\n" +
                "#include <string>\n" +
                "#include <cstdio>\n" +
                "#include <algorithm>  \n" +
                "\n" +
                "using namespace std;\n" +
                "\n" +
                "int getNearestForL(long *arr, int n, long k){\n" +
                "    int start = 0;\n" +
                "    int end = n - 1;\n" +
                "    while (true){\n" +
                "\n" +
                "        int center = (end+start)/2;\n" +
                "        if (end < start){\n" +
                "            return -1;\n" +
                "        }\n" +
                "        if (arr[center] >= k){\n" +
                "            if (center - 1 <= 0 || arr[center - 1] < k)\n" +
                "                return center;\n" +
                "            end = center - 1;\n" +
                "        }\n" +
                "        else if (arr[center] < k){\n" +
                "            if (center + 1 == n)\n" +
                "                return -1;\n" +
                "            if (arr[center + 1] >= k){\n" +
                "                return center + 1;\n" +
                "            }\n" +
                "            start = center + 1;\n" +
                "        }\n" +
                "    }\n" +
                "    return -1;\n" +
                "}\n" +
                "\n" +
                "int getNearestForR(long *arr, int n, long k){\n" +
                "    int start = 0;\n" +
                "    int end = n - 1;\n" +
                "    while (true){\n" +
                "\n" +
                "        int center = (end+start)/2;\n" +
                "        if (end < start){\n" +
                "            return -1;\n" +
                "        }\n" +
                "        if (arr[center] <= k){\n" +
                "            if (center + 1 == n || arr[center + 1] > k)\n" +
                "                return center;\n" +
                "            start = center + 1;\n" +
                "        }\n" +
                "        else if (arr[center] > k){\n" +
                "            if (center - 1 < 0)\n" +
                "                return -1;\n" +
                "            if (arr[center - 1] <= k){\n" +
                "                return center - 1;\n" +
                "            }\n" +
                "            end = center - 1;\n" +
                "        }\n" +
                "    }\n" +
                "    return -1;\n" +
                "}\n" +
                "\n" +
                "int main(void)\n" +
                "{\n" +
                "\tios_base::sync_with_stdio(false);\n" +
                "\tcin.tie(NULL);\n" +
                "\n" +
                "    int n, k;\n" +
                "    cin >> n >> k;\n" +
                "    long *starts = new long[n];\n" +
                "    long *ends = new long[n];\n" +
                "\n" +
                "    \n" +
                "    for (int i = 0; i < n; i++){\n" +
                "        int e1, e2;\n" +
                "        cin >> e1 >> e2;\n" +
                "        if (e1 <= e2){\n" +
                "            starts[i] = e1;\n" +
                "            ends[i] = e2;\n" +
                "        } else {\n" +
                "            starts[i] = e2;\n" +
                "            ends[i] = e1;\n" +
                "        }\n" +
                "    }    \n" +
                "\n" +
                "    sort(starts, starts + n);\n" +
                "    sort(ends, ends + n);\n" +
                "\n" +
                "    string result = \"\";\n" +
                "    for (int i = 0; i < k; i++){\n" +
                "        long el;\n" +
                "        cin >> el;\n" +
                "\n" +
                "        long starts_before = getNearestForR(starts, n, el);\n" +
                "        if (starts_before == -1)\n" +
                "            result += \"0 \";\n" +
                "        else{\n" +
                "            long ends_before = getNearestForR(ends, n, el - 1);\n" +
                "            result += to_string(starts_before - ends_before) + \" \";\n" +
                "        }\n" +
                "        \n" +
                "    }\n" +
                "    cout << result;\n" +
                "\n" +
                "\treturn 0;\n" +
                "}";

        System.out.println("Start performance");
        OsiristherNative on = new OsiristherNative(resultsList);
        on.setHandler(handler);
        on.testSource(13, 1, source, Language.CPP);
        on.testSource(87, 1, source, Language.JAVA);
        on.testSource(65, 1, source, Language.CPP);
        on.testSource(3, 1, source, Language.CPP);
        on.testSource(58, 1, source + " 5", Language.CPP);
        on.testSource(16, 1, source, Language.CPP);
        on.testSource(41, 1, incorrectSource, Language.CPP);
        on.testSource(74, 1, source, Language.CPP);
        on.free();
        System.out.println("End performance");
    }
}
