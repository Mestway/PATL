/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class AByteBaseType extends PBaseType
{
    private TByte _byte_;

    public AByteBaseType()
    {
        // Constructor
    }

    public AByteBaseType(
        @SuppressWarnings("hiding") TByte _byte_)
    {
        // Constructor
        setByte(_byte_);

    }

    @Override
    public Object clone()
    {
        return new AByteBaseType(
            cloneNode(this._byte_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAByteBaseType(this);
    }

    public TByte getByte()
    {
        return this._byte_;
    }

    public void setByte(TByte node)
    {
        if(this._byte_ != null)
        {
            this._byte_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._byte_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._byte_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._byte_ == child)
        {
            this._byte_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._byte_ == oldChild)
        {
            setByte((TByte) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
