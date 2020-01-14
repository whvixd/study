package com.github.whvixd.demo

import com.github.whvixd.BaseTest
import org.mockito.InOrder


import static org.mockito.Mockito.*
import static org.mockito.Mockito.inOrder

/**
 * Created by wangzhx on 2020/1/6.
 */
class MockitoDemo extends BaseTest {
    def "verify-times(方法执行次数)"() {
        given:
        def mockList = mock(List.class)
        mockList.add(1)

        mockList.add(2)
        mockList.add(2)


        mockList.add(3)
        mockList.add(3)
        mockList.add(3)

        mockList.clear()

        //times(1)调用一次
        verify(mockList, times(0)).add(0)
        verify(mockList, times(1)).add(1)
        verify(mockList, times(2)).add(2)
        verify(mockList, times(3)).add(3)
        verify(mockList, never()).add(4)

        //对象没有执行方法
        verifyZeroInteractions(mock(List.class))
        verify(mockList).clear()


    }

    def "when"() {
        given:
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("first")
        when(mockedList.get(1)).thenThrow(new RuntimeException())

        // the following prints "first"
        System.out.println(mockedList.get(0))


        try {
            System.out.println(mockedList.get(1))
        } catch (Exception ignored) {
        }

        when(mockedList.get(anyInt())).thenReturn("any element")
        println mockedList.get(1)

    }

    def "inOrder(方法执行顺序)"() {
        setup:
        List singleMock = mock(List.class)

        singleMock.add(1)
        singleMock.add(2)

        //方法的调用顺序，add(1) 先调用，add(2) 后调用
        InOrder singleInOrder = inOrder(singleMock)
        singleInOrder.verify(singleMock).add(1)
        singleInOrder.verify(singleMock).add(2)

        List firstMock = mock(List.class)
        List secondMock = mock(List.class)

        firstMock.add(1)
        secondMock.add(1)
        firstMock.add(2)
        secondMock.add(2)

        InOrder multipleInOrder = inOrder(firstMock, secondMock)
        multipleInOrder.verify(firstMock).add(1)
        multipleInOrder.verify(secondMock).add(1)
        multipleInOrder.verify(firstMock).add(2)
        multipleInOrder.verify(secondMock).add(2)

    }

}
